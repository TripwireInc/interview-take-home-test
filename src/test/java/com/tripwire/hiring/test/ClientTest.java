/*
 * Copyright (c) 2015, Tripwire, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *  o Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * -----------------------------------------------------------------------
 *
 * Written by: bobl
 *
 * =======================================================================
 */
package com.tripwire.hiring.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientTest {

    @Test
    public void testSendAndReceiveExitsOnEmptyInput() throws Exception {
        PrintStream conOut = mock(PrintStream.class);
        PrintWriter sockOut = mock(PrintWriter.class);
        BufferedReader sockIn = mock(BufferedReader.class);
        BufferedReader conIn = mock(BufferedReader.class);
        when(conIn.readLine()).thenReturn("");

        Client.sendAndReceive(conOut, sockOut, sockIn, conIn);
        verifyZeroInteractions(sockIn);
        verifyZeroInteractions(sockOut);
        verify(conOut).println("Client is exiting.");
    }

    @Test
    public void testSendAndReceiveExitsOnExitInput() throws Exception {
        PrintStream conOut = mock(PrintStream.class);
        PrintWriter sockOut = mock(PrintWriter.class);
        BufferedReader sockIn = mock(BufferedReader.class);
        BufferedReader conIn = mock(BufferedReader.class);
        when(conIn.readLine()).thenReturn("exit");

        Client.sendAndReceive(conOut, sockOut, sockIn, conIn);
        verifyZeroInteractions(sockIn);
        verifyZeroInteractions(sockOut);
        verify(conOut).println("Client is exiting.");
    }

    @Test
    public void testSendAndReceiveExitsOnExitInputCaseInsensitive() throws Exception {
        PrintStream conOut = mock(PrintStream.class);
        PrintWriter sockOut = mock(PrintWriter.class);
        BufferedReader sockIn = mock(BufferedReader.class);
        BufferedReader conIn = mock(BufferedReader.class);
        when(conIn.readLine()).thenReturn("ExIT");

        Client.sendAndReceive(conOut, sockOut, sockIn, conIn);
        verifyZeroInteractions(sockIn);
        verifyZeroInteractions(sockOut);
        verify(conOut).println("Client is exiting.");
    }

    @Test
    public void testSendAndReceiveExitsOnQuitInput() throws Exception {
        PrintStream conOut = mock(PrintStream.class);
        PrintWriter sockOut = mock(PrintWriter.class);
        BufferedReader sockIn = mock(BufferedReader.class);
        BufferedReader conIn = mock(BufferedReader.class);
        when(conIn.readLine()).thenReturn("quit");

        Client.sendAndReceive(conOut, sockOut, sockIn, conIn);
        verifyZeroInteractions(sockIn);
        verifyZeroInteractions(sockOut);
        verify(conOut).println("Client is exiting.");
    }

    @Test
    public void testSendAndReceiveExitsOnQuitInputCaseInsensitive() throws Exception {
        PrintStream conOut = mock(PrintStream.class);
        PrintWriter sockOut = mock(PrintWriter.class);
        BufferedReader sockIn = mock(BufferedReader.class);
        BufferedReader conIn = mock(BufferedReader.class);
        when(conIn.readLine()).thenReturn("QuIt");

        Client.sendAndReceive(conOut, sockOut, sockIn, conIn);
        verifyZeroInteractions(sockIn);
        verifyZeroInteractions(sockOut);
        verify(conOut).println("Client is exiting.");
    }


    @Test
    public void testSendAndReceiveRoundTrip() throws Exception {
        PrintStream conOut = mock(PrintStream.class);
        PrintWriter sockOut = mock(PrintWriter.class);
        BufferedReader sockIn = mock(BufferedReader.class);
        when(sockIn.readLine()).thenReturn("Hello World");
        BufferedReader conIn = mock(BufferedReader.class);
        when(conIn.readLine()).thenReturn("Hello World").thenReturn("QuIt");

        Client.sendAndReceive(conOut, sockOut, sockIn, conIn);
        verify(sockOut).println("Hello World");
        verify(conOut).println("Hello World");
        verify(conOut).println("Client is exiting.");
    }
}
