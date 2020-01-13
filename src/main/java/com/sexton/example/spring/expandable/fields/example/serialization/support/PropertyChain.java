package com.sexton.example.spring.expandable.fields.example.serialization.support;

import java.util.Deque;

/**
 * Represents a chain of property/member fields of an object.
 * <p>
 * TODO: Properties might not want to be just represented by strings. Expand the idea of this interface to accept any form of data as representation for a property
 */
public interface PropertyChain {
    /**
     * Checks if the give chain is a sub chain of the current property chain instance.
     * Ex. Person.Contact.Information.containsSubChain(Person.Contact) returns true
     *
     * @param chain The chain to check if is a sub chain of this instance
     * @return Whether or not the given chain was a sub chain of this instance
     */
    boolean containsSubChain(PropertyChain chain);

    /**
     * Same concept as {@link PropertyChain#containsSubChain(PropertyChain)} except case is ignored
     *
     * @param chain The chain to check if is a sub chain of this instance
     * @return Whether or not the given chain was a sub chain of this instance
     */
    boolean containsSubChainIgnoreCase(PropertyChain chain);

    /**
     * @return The member names in an ordered iterable.
     */
    Deque<String> getMembers();
}
