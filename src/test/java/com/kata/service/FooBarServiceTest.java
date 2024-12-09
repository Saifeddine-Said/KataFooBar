package com.kata.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FooBarServiceTest {
    @Test
    void testOutOfRange() {
        FooBarService service = new FooBarService();
        assertEquals("-1", service.checkNumber(-1));
        assertEquals("101", service.checkNumber(101));
    }

    @Test
    void testDivisibleBy1() {
        FooBarService service = new FooBarService();
        assertEquals("1", service.checkNumber(1));
    }

    @Test
    void testDivisibleBy3AndContains3() {
        FooBarService service = new FooBarService();
        assertEquals("FOOFOO", service.checkNumber(3));
        assertEquals("FOOFOOFOO", service.checkNumber(33));
    }

    @Test
    void testDivisibleBy5AndContains5() {
        FooBarService service = new FooBarService();
        assertEquals("BARBAR", service.checkNumber(5));
    }

    @Test
    void testContains7() {
        FooBarService service = new FooBarService();
        //assertEquals("QUIX",service.checkNumber(7));
        assertEquals("QUIXQUIX", service.checkNumber(77));
    }

    @Test
    void testDivisibleBy3And5() {
        FooBarService service = new FooBarService();
        assertEquals("FOOBARBAR", service.checkNumber(15));
        assertEquals("FOOBARFOO", service.checkNumber(30));
    }

    @Test
    void testDivisibleBy5AndContains3() {
        FooBarService service = new FooBarService();
        assertEquals("BARFOOBAR", service.checkNumber(35));
    }

    @Test
    void testDivisibleBy5AndContains7() {
        FooBarService service = new FooBarService();
        assertEquals("FOOBARQUIXBAR", service.checkNumber(75));
    }

    @Test
    void testContains3AndContains7() {
        FooBarService service = new FooBarService();
        assertEquals("FOOQUIX", service.checkNumber(37));
    }
}