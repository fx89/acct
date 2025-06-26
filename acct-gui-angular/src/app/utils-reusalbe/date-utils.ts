
/**
 * Returns the number of seconds which have passed since Epoch
 */
export function currentTimestampInSeconds() : number {
    return Math.floor(Date.now() / 1000)
}