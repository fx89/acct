/**
 * Extracts the last token from the given string, after splitting it by the given separator
 * 
 * @param string    the given string
 * @param separator the given separator
 * @returns         the extracted token
 */
export function extractLastToken(string:string, separator:string) : string {
    const tokens = string.split(separator);
    return tokens[tokens.length - 1];
}

/**
 * Extracts the first token from the given string, after splitting it by the given separator
 * 
 * @param string    the given string
 * @param separator the given separator
 * @returns         the extracted token
 */
export function extractFirstToken(string:string, separator:string) : string {
    return extractToken(string, separator, 0)
}

/**
 * Extracts the Nth token from the given string, after splitting it by the given separator
 * 
 * @param string     the given string
 * @param separator  the given separator
 * @param tokenIndex the value of N
 * @returns          the extracted token
 */
export function extractToken(string:string, separator:string, tokenIndex:number) : string {
    const tokens = string.split(separator);
    return tokens[tokenIndex];
}