package com.mamay.inspection.factory;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.EmptyCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.enums.CommandType;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(FormParameter.COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            log.error(e);
        }
        return current;
    }
}