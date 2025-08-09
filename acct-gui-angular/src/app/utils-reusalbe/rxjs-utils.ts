import { Predicate } from "@angular/core";
import { filter, first, interval, map, Observable, Subscriber } from "rxjs";

/**
 * Creates an observable, of the given data type, that completes without producing anything.
 */
export function emptyObservable<RET>() : Observable<RET> {
    return new Observable<RET>(subscriber => {
        subscriber.complete()
    })
}

/**
 * Creates an observable that produces the referenced item
 * 
 * @param item the referenced item
 */
export function newObservable<T>(item:T) : Observable<T> {
    return new Observable<T>(subscriber => {
        complete(subscriber, item)
    })
}

/**
 * Defines a generic transformation function that takes an input parameter of a given type
 * and outputs an item of another type.
 */
export type Transform<IN,OUT> = (input:IN) => OUT

/**
 * Subscribes to the referenced observable to apply the given transform to the observed items.
 * A new observable, of the output data type, is returned. The error event is piped from the
 * input observable to the output observable and so is the complete event.
 * 
 * @param observable the referenced observable
 * @param transform  the given transform
 * @returns the new observable, which supplies the transformation result
 */
export function errorPipingObservableTransform<IN,OUT>(
    observable : Observable<IN>,
    transform  : Transform<IN,OUT>
) : Observable<OUT>
{
    return new Observable<OUT>(subscriber => {
        observable.subscribe({
            next: (item:IN) => subscriber.next(transform(item)),
            error: (err:any) => subscriber.error(err),
            complete: () => subscriber.complete(),
        })
    })
}

/**
 * Subscribes to the referenced observable to apply the referenced consumer as the "next"
 * callback. Errors are piped. The "complete" signal is expected to be snet by the referenced
 * comsumer.
 * 
 * @param observable the referenced observable
 * @param consumer   the referenced consumer
 * @returns 
 */
export function errorPipingObservableOperation<IN,OUT>(
    observable : Observable<IN>,
    consumer   : (item:IN, subscriber:Subscriber<OUT>) => void
) : Observable<OUT> {
    return new Observable<OUT>(subscriber => {
        observable.subscribe({
            next: (item:IN) => consumer(item, subscriber),
            error: (err:any) => subscriber.error(err)
        })
    })
}

/**
 * Subscribes to the referenced observable and executes the referenced consumer on the observed
 * data item. If the referenced observable completes or produces erros, these are automatically
 * piped to the referenced subscriber.
 * 
 * @param observable the referenced observable
 * @param subscriber the referenced subscriber
 * @param consumer   the referenced consumer, which takes item produced by the referenced observable
 *                   and the referenced subscriber as parameters
 */
export function errorPipingObservableConsumer<IN,OUT>(
    observable : Observable<IN>,
    subscriber : Subscriber<OUT>,
    consumer   : (item:IN, subscriber:Subscriber<OUT>) => void
) : void {
    observable.subscribe({
        next: (item:IN) => consumer(item, subscriber),
        error: (err:any) => subscriber.error(err),
        complete: () => subscriber.complete()
    })
}

/**
 * Subscribes to the referenced observable to apply the referenced error consumer as
 * error callback function. The next and complete callbacks are piped.
 * 
 * @param observable    the referenced observable
 * @param errorConsumer the referenced error consumer
 */
export function errorConsumingObservableOperation<IN,ERR>(
    observable : Observable<IN>,
    errorConsumer : ((err:ERR) => void)
) : Observable<IN> {
    return new Observable<IN>(subscriber => {
        observable.subscribe({
            next: (data:IN) => subscriber.next(data),
            complete: () => subscriber.complete(),
            error: err => errorConsumer(err)
        })
    })
}

/**
 * Subscribes to the referenced observable to apply the referenced transform and outputs
 * a new observable for the transformation result. The referenced error consumer is called
 * in case of errors.
 * 
 * @param observable    the referenced observable
 * @param transform     the referenced transform
 * @param errorConsumer the referenced error consumer
 */
export function errorConsumingObservableTransform<IN,OUT,ERR>(
    observable : Observable<IN>,
    transform : Transform<IN,OUT>,
    errorConsumer : ((err:ERR) => void)
) : Observable<OUT> {
    return new Observable<OUT>(subscriber => {
        observable.subscribe({
            next: (data:IN) => subscriber.next(transform(data)),
            complete: () => subscriber.complete(),
            error: err => errorConsumer(err)
        })
    })
}

/**
 * Sends the given value to the referenced subscriber and then sends the complete() signal
 * 
 * @param subscriber the referenced subscriber
 * @param value      the given value
 */
export function complete<T>(subscriber:Subscriber<T>, value:T) : void {
    subscriber.next(value)
    subscriber.complete()
}

/**
 * Pipes one of two observables, depending on the result of the referenced condition.
 * If the referenced condition evaluates to true, then the observableIfTrue is piped.
 * If the referenced condition evaluates to false, then the observableIfFalse is piped.
 * Errors are piped either way.
 * 
 * @param condition         the referenced condition
 * @param observableIfTrue 
 * @param observableIfFalse 
 */
export function errorPipingConditionalObservable<T>(
    condition : (() => boolean),
    observableIfTrue : () => Observable<T>,
    observableIfFalse : () => Observable<T>
) {
    return new Observable<T>(subscriber => {
        if (condition()) {
            errorPipingObservableConsumer(
                observableIfTrue(),
                subscriber,
                (item, subscriber) => complete(subscriber, item)
            )
        } else {
            errorPipingObservableConsumer(
                observableIfFalse(),
                subscriber,
                (item, subscriber) => complete(subscriber, item)
            )
        }
    })
}

/**
 * Returns an observable that is triggered when the condition specified by the given predicate is
 * satisfied. The predicate is applied once every X number of milliseconds, where X is given by
 * the stepTimeMs. If stepTimeMs is not provided, then the default value of 100ms is used for X.
 * 
 * @param condition  the given predicate
 * @param stepTimeMs the number of milliseconds to wait between any two applications of
 *                   the predicate
 * @return an observable that is triggered only when the condition is satisfied
 */
export function waitForCondition(condition:Predicate<void>, stepTimeMs?:number) : Observable<void> {
    const waitTimeMs : number = stepTimeMs ?? 100

    return interval(waitTimeMs).pipe(
        filter(() => condition.apply(undefined)),
        first(),
        map(() => void 0)
    );
    
}
