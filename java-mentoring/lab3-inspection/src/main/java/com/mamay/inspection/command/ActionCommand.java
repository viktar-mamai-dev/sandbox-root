package com.mamay.inspection.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mamay Viktor Base interface for all commands
 *     <p>Uses pattern Command
 */
public interface ActionCommand {

  String execute(HttpServletRequest request);
}
