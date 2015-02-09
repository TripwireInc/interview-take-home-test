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

import javax.net.SocketFactory;
import java.io.*;
import java.net.Socket;

public class Client {

  public static final String SERVER_ADDRESS = "localhost";

  public static void main(String[] args) {
    PrintStream consoleOut = System.out;
    try {
      Socket socket = SocketFactory.getDefault().createSocket(SERVER_ADDRESS, Server.SERVER_PORT);
      PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

      sendAndReceive(consoleOut, socketWriter, socketReader, inputReader);

    } catch (IOException ioex) {
      consoleOut.println("Exception caught - exiting. Reason = " + ioex.getMessage());
    }
  }

  protected static void sendAndReceive(
          PrintStream consoleOut,
          PrintWriter socketWriter,
          BufferedReader socketReader,
          BufferedReader consoleIn) throws IOException {
    while (true){
      String line = consoleIn.readLine();
      if(line.isEmpty() || line.equalsIgnoreCase("exit")|| line.equalsIgnoreCase("quit")){
        consoleOut.println("Client is exiting.");//this could be "broken froma  refactoring ;) change console out back to System.out
        break;
      }
      socketWriter.println(line);
      System.out.println(socketReader.readLine());
    }
  }
}
