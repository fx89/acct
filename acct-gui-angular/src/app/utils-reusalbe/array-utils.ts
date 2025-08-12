
/**
 * Returns a new array containing the soft-copied distinct values of the referenced array.
 * The distinctiveness is based on the keys resulted from the given key extractor function.
 * If the key extractor is not provided, then it defaults to JSON.stringify().
 * 
 * @param array          the referenced array
 * @param keyExtractor   optional key extractor function
 */
export function distinctElementsArray<T,K>(array:T[], keyExtractor?:((t:T)=>K)) : T[] {
    // Garbage in, garbage out
    if (!array || array.length == 0) {
        return array
    }

    // Resolve the key extractor function
    const resolvedKeyExtractor : (t:T)=>K|string = keyExtractor ?? JSON.stringify

    // Create a temporary array to cache the already computed keys
    const keys : (K|string)[] = []

    // Create the new array
    const ret : T[] = []

    // Add elements to the new array, as long as they are not already added
    array.forEach(element => {
        // Extract the key
        const key = resolvedKeyExtractor(element)

        // If the key does not alredy exist
        if (keys.indexOf(key) == -1) {
            // Add the key to the keys array
            keys.push(key)

            // Add the value to the new array
            ret.push(element)
        }
    })

    // Return a reference to the new array
    return ret
}

/**
 * Removes the referenced element from the referenced array. If the referenced
 * element does not exist in the referenced array, then nothing happens.
 * @param array     the referenced array
 * @param element   the referenced element
 */
export function removeArrayElement(array:any[], element:any) : void {
    const index = array.indexOf(element, 0);
    if (index > -1) {
      array.splice(index, 1);
    }
}

/**
 * Merges the two referenced arrays of elements of the same type into one single
 * array that contains distinct elements. The distinctiveness is given by the
 * referenced key extractor.
 * 
 * @param array1       one of the arrays to be merged
 * @param array2       the other array to be merged
 * @param keyExtractor a mapping function that extracts the unique key from
 *                     objects that are found in the two arrays to be merged
 */
export function mergeArrays<T,K>(array1:T[], array2:T[], keyExtractor?:((t:T)=>K)) : T[] {
    return distinctElementsArray(array1.concat(array2), keyExtractor)
}

/**
 * Flattens the referenced array of arrays
 * 
 * @param arrays the referenced array of arrays
 */
export function flattenArrays<T>(arrays:T[][]) : T[] {
    const arr : T[] = []

    arrays.forEach(array => {
        if (array) {
            array.forEach(element => {
                arr.push(element)
            })
        }
    })

    return arr
}