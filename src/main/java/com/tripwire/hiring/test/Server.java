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


import com.google.common.annotations.VisibleForTesting;

import javax.net.ServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static final int SERVER_PORT = 10000;

  public static void main(String[] args) throws Exception {
    ServerSocketFactory socketFactory = ServerSocketFactory.getDefault();
    ServerSocket serverSocket = null;
    try {
      serverSocket = socketFactory.createServerSocket(SERVER_PORT);

      listenAndRespond(serverSocket);

    } catch (IOException e) {
      System.out.printf("Socket Failure: %s", e.getMessage());
    } finally {
      if (serverSocket != null) {
        serverSocket.close();
      }
    }
  }

  @VisibleForTesting
  protected static void listenAndRespond(ServerSocket serverSocket) throws IOException {
    DataHandler handler = new EchoDataHandler();
    String data = null;
    Socket clientSocket = null;
    while (true) {
      clientSocket = serverSocket.accept();
      PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      handleClientData(handler, writer, reader);
      clientSocket.close();
    }
  }

  @VisibleForTesting
  protected static void handleClientData(DataHandler handler, PrintWriter writer, BufferedReader reader) throws IOException {
    String data;
    while ((data = reader.readLine()) != null) {
      writer.println(handler.handle(data));
    }
  }

  public interface DataHandler {
    String handle(String data);
  }

  public static class EchoDataHandler implements DataHandler{
    @Override
    public String handle(String data) {
      System.out.println("Received data = " + data);
      return data;
    }
  }
}
