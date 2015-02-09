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
import org.mockito.Matchers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServerTest {
  @Test
  public void testHandleClientDataDoesNotWriteWhenInputCloses() throws Exception {
    BufferedReader reader = mock(BufferedReader.class);
    when(reader.readLine()).thenReturn(null);
    PrintWriter writer = mock(PrintWriter.class);
    Server.DataHandler dataHandler = mock(Server.DataHandler.class);
    Server.handleClientData(dataHandler, writer, reader);
    verifyZeroInteractions(dataHandler);
    verifyZeroInteractions(writer);
  }

  @Test
  public void testHandleClientDataWritesWhenInputHasData() throws Exception {
    BufferedReader reader = mock(BufferedReader.class);
    when(reader.readLine()).thenReturn("hello world\n").thenReturn(null);
    PrintWriter writer = mock(PrintWriter.class);
    Server.DataHandler dataHandler = new Server.EchoDataHandler();

    Server.handleClientData(dataHandler, writer, reader);
    verify(writer).println("hello world\n");
  }

  @Test
  public void testHandleClientDataWritesWhenInputHasMultipleLinesOfData() throws Exception {
    BufferedReader reader = mock(BufferedReader.class);
    when(reader.readLine()).thenReturn("hello world\n").thenReturn("goodbye cruel world\n").thenReturn("fubar\n").thenReturn(null);
    PrintWriter writer = mock(PrintWriter.class);
    Server.DataHandler dataHandler = new Server.EchoDataHandler();

    Server.handleClientData(dataHandler, writer, reader);
    verify(writer, times(3)).println(anyString());
  }

  @Test(expected = SocketTimeoutException.class)
  public void testExitWhenSocketAcceptTimesOut() throws Exception{

    ServerSocket serverSocket = mock(ServerSocket.class);
    when(serverSocket.accept()).thenThrow(new SocketTimeoutException());

    Server.listenAndRespond(serverSocket);
  }
}
