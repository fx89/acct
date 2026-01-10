/**
 * Returns true if the given item reference points to an actual item. Returns false otherwise.
 * 
 * @param item the given item reference
 * @returns 
 */
export function isDefined<T>(item:T) : boolean {
    if (item != undefined && item != null) {
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

/**
 * Returns true if the referenced string is a number, or false if it's not
 */
export function isNumber(string: any) : boolean {
    // Parse the string into a float
    const number = parseFloat(string)

    // If the parsing did not go well, then the string does not contain a number
    if(isNaN(number)) {
        return false
    }

    // If theparsing went well, then test it with the regex
    return /^[+-]?\d+(\.\d+)?$/.test(string)
}