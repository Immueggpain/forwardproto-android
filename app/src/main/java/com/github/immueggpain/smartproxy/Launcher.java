package com.github.immueggpain.smartproxy;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(description = "Please report issues at https://github.com/Immueggpain/forwardproto/issues",
		name = "smartproxy", mixinStandardHelpOptions = true, version = Launcher.VERSTR,
		subcommands = {HelpCommand.class, Smartproxy.class, SmartproxyServer.class})
public class Launcher implements Callable<Void> {

	public static final String VERSTR = "2.0.5";

	public static void main(String[] args) {
		int exitCode = new CommandLine(new Launcher()).setCaseInsensitiveEnumValuesAllowed(true)
				.setUsageHelpLongOptionsMaxWidth(40).setUsageHelpAutoWidth(true).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Void call() throws Exception {
		CommandLine.usage(this, System.out);
		return null;
	}

}
