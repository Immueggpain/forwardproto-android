package com.github.immueggpain.smartproxy.ui;

import android.content.res.Resources;

import com.github.immueggpain.smartproxy.Smartproxy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LauncherToBackend {

	public void run(@NotNull ClientSettings settings, Resources resources) throws Exception {
		Smartproxy smartproxy = new Smartproxy();
		smartproxy.local_listen_ip = settings.local_listen_ip;
		smartproxy.local_listen_port = settings.local_listen_port;
		smartproxy.server_ip = settings.server_ip;
		smartproxy.server_port = settings.server_port;
		smartproxy.passwordString = settings.password;
		smartproxy.logfile = settings.logfile;
		smartproxy.call();
	}

	public static class ClientSettings {
		public String local_listen_ip;
		public int local_listen_port;
		public String server_ip;
		public int server_port;
		public String password;
		public String logfile;
	}

}
