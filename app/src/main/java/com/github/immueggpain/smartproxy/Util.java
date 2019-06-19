/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2018 Immueggpain
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package com.github.immueggpain.smartproxy;

import java.io.Closeable;
import java.net.Socket;

public class Util {

	/** cull out some useless exception, silent ignore them */
	public static <E extends Exception> void cullException(RunnableE func, Class<E> exceptionClz, String exceptionMsg)
			throws Exception {
		try {
			func.run();
		} catch (Exception e) {
			if (exceptionClz.isInstance(e) && e.getMessage().equals(exceptionMsg)) {
				// ignore
			} else
				throw e;
		}
	}

	@FunctionalInterface
	public interface RunnableE {
		void run() throws Exception;
	}

	public static void closeQuietly(Closeable s) {
		try {
			s.close();
		} catch (Throwable ignore) {
		}
	}

	/** just close s ASAP quietly. used when s is already broken */
	public static void abortiveCloseSocket(Socket s) {
		try {
			s.setSoLinger(true, 0);
		} catch (Throwable ignore) {
		}
		try {
			s.close();
		} catch (Throwable ignore) {
		}
	}

	/** print if exception */
	public static void orderlyCloseSocket(Socket s) {
		try {
			s.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
