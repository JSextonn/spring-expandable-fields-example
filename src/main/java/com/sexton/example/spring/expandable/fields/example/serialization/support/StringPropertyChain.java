package com.sexton.example.spring.expandable.fields.example.serialization.support;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class StringPropertyChain implements PropertyChain {
    public static final String SEPARATOR = ".";

    private final Deque<String> members;

    public StringPropertyChain() {
        members = new LinkedList<>();
    }

    /**
     * Creates a new property chain from given deque of members
     *
     * @param members
     */
    public StringPropertyChain(final Deque<String> members) {
        this.members = members;
    }

    /**
     * Creates a new property chain based on another
     *
     * @param children
     */
    public StringPropertyChain(final PropertyChain children) {
        this.members = children != null ? children.getMembers() : new LinkedList<>();
    }

    public static PropertyChain fromNamePath(final String namePath) {
        final String[] members = namePath.split(SEPARATOR);

        final List<String> memberList = members.length == 0 ?
                Collections.singletonList(namePath) :
                Arrays.asList(members);

        final Deque<String> membersDeque = new LinkedList<>(memberList);

        return new StringPropertyChain(membersDeque);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsSubChain(final PropertyChain chain) {
        return toString().startsWith(chain.toString());
    }

    /**
     * {@inheritDoc}
     *
     * @param chain The chain to check if is a sub chain of this instance
     * @return
     */
    @Override
    public boolean containsSubChainIgnoreCase(final PropertyChain chain) {
        return toString().toLowerCase().startsWith(chain.toString().toLowerCase());
    }

    @Override
    public String toString() {
        return String.join(SEPARATOR, members);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public Deque<String> getMembers() {
        return new LinkedList<>(members);
    }
}
