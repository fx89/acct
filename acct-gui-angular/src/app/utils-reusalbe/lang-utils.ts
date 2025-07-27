/**
 * Returns true if the given item reference points to an actual item. Returns false otherwise.
 * 
 * @param item the given item reference
 * @returns 
 */
export function isDefined<T>(item:T) : boolean {
    if (item) {
        return true
    }

    return false
}

/**
 * If the given item reference points to an actual item, then the reference is returned. If
 * not, then an error is thrown.
 * 
 * @param item 
 */
export function throwIfNotDefined<T>(item:T|undefined) : T {
    if (isDefined(item)) {
        return item as T
    }

    throw new Error("The given item reference is undefined")
}