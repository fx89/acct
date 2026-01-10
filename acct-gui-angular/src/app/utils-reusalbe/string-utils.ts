import { isDefined } from "./lang-utils";

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

/**
 * Returns true if the given string reference is null or undefined or if the referenced sting is empty
 */
export function isEmptyString(string?:string) : boolean {
    return !isDefined(string) || (string?.length == 0)
}