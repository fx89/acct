/**
 * Wraps the Storage class, to provide serialization support and extended features
 */
class ObjectStorage {

    constructor (private storage : Storage) {

    }

    /**
     * Stores the referenced object in its serialized form, under the given key.
     * @param key    the given key
     * @param object the referenced object
     */
    public setItem<T>(key : string, object : T) : void {
        this.storage.setItem(key, JSON.stringify(object))
    }

    /**
     * Retrieves and deserializes the object stored under the given key. If the
     * given key does not exist in the store, then null is returned.
     * @param key the given key
     */
    public getItem<T>(key : string) : T | null {
        // Get the serialized item (if any)
        const serializedItem : any = this.storage.getItem(key)

        // If the serialized item exists in the store, then deserialize it and return a reference
        if (serializedItem) {
            return JSON.parse(serializedItem)
        }

        // If the serialized item does not exist in the store, then return null
        return null
    }

    /**
     * Checks if the given key exists in the store.
     * @param key the given key
     * @returns true if the key exists, false otherwise
     */
    public hasItem(key : string) : boolean {
        return (this.storage.getItem(key) ? true : false)
    }

    /**
     * Removes the key/value pair with the given key, if a key/value pair with the given key exists
     * @param key the given key
     */
    public removeItem(key : string) : void {
        this.storage.removeItem(key)
    }

    /**
     * Returns the number of keys stored in the store
     */
    public length() : number {
        return this.storage.length
    }

    /**
     * Returns the key at the given index. Returns null if there is no key at the given index.
     * @param index the given index
     */
    public key(index:number) : string | null {
        return this.storage.key(index)
    }

    /**
     * Clears the store
     */
    public clear() : void {
        this.storage.clear()
    }
}

/**
 * Returns the sessionStorage as an ObjectStorage class that performs serialization
 * and deserialization when storing and retrieving objects and provides additional
 * features.
 */
export function sessionObjectStorage() : ObjectStorage {
    return new ObjectStorage(sessionStorage)
}

/**
 * Returns the localStorage as an ObjectStorage class that performs serialization
 * and deserialization when storing and retrieving objects and provides additional
 * features.
 */
export function localObjectStorage() : ObjectStorage {
    return new ObjectStorage (localStorage)
}