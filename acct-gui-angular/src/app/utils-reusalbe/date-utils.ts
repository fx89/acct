
/**
 * Returns the number of seconds which have passed since Epoch
 */
export function currentTimestampInSeconds() : number {
    return Math.floor(Date.now() / 1000)
}

/**
 * Returns the ISO representation of the referenced date
 */
export function dateToIsoString(date : Date) : string {
    const year  : number = date.getFullYear()
    const month : number = date.getMonth() + 1
    const day   : number = date.getDate()

    return (
      year + "-" + 
      (month < 10 ? "0" : "") + month + "-" +
      (day < 10 ? "0" : "") + day
    )
}