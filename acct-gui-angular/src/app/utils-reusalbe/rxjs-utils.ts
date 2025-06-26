import { Observable } from "rxjs";

/**
 * Creates an observable, of the given data type, that completes without producing anything.
 */
export function emptyObservable<RET>() : Observable<RET> {
    return new Observable<RET>(subscriber => {
        subscriber.complete()
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
            next(item:IN) {
                subscriber.next(transform(item))
            },
            error(err:any) {
                subscriber.error(err)
            },
            complete() {
                subscriber.complete()
            },
        })
    })
}