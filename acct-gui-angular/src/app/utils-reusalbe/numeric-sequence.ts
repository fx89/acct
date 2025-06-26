/**
 * A numeric sequence is an iterator that iterates between a given minimum and maximum value.
 * When the maximum value has been reached, the current value resets and the iterator goes on
 * forever.
 */
export class NumericSequence implements Iterable<number>, Iterator<number> {
  private current : number;

  constructor(
    private readonly min: number,
    private readonly max: number
) {
    if (min > max) {
        throw "Bad parameters given when construction AtomicSequence. Min must be smaller than or equal to max."
    }

    this.current = min
}

  next(): IteratorResult<number> {
    const value = this.current;

    this.current = this.current + 1;

    if (this.current > this.max) {
        this.current = this.min
    }

    return { value, done: false };
  }

  [Symbol.iterator](): Iterator<number> {
    return this;
  }

  reset(): void {
    this.current = this.min;
  }
}