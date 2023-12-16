package com.mamay.inspection.enums;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ChangeLocaleCommand;
import com.mamay.inspection.command.RedirectionCommand;
import com.mamay.inspection.command.magazine.AddMagazineCommand;
import com.mamay.inspection.command.magazine.DeleteMagazineCommand;
import com.mamay.inspection.command.magazine.NewMagazineCommand;
import com.mamay.inspection.command.magazine.SelectMagazineCommand;
import com.mamay.inspection.command.magazine.ShowMagItemCommand;
import com.mamay.inspection.command.magazine.ShowMagListCommand;
import com.mamay.inspection.command.magazine.UpdateMagazineCommand;
import com.mamay.inspection.command.reservation.AddReservationCommand;
import com.mamay.inspection.command.reservation.DeleteReservationCommand;
import com.mamay.inspection.command.reservation.NewReservationCommand;
import com.mamay.inspection.command.reservation.ShowResListCommand;
import com.mamay.inspection.command.reservation.ShowUserItemCommand;
import com.mamay.inspection.command.reservation.UpdateReservationCommand;
import com.mamay.inspection.command.subscription.AddSubscriptionCommand;
import com.mamay.inspection.command.subscription.DeleteSubscriptionCommand;
import com.mamay.inspection.command.subscription.NewSubscriptionCommand;
import com.mamay.inspection.command.subscription.SelectSubscriptionCommand;
import com.mamay.inspection.command.subscription.UpdateSubscriptionCommand;
import com.mamay.inspection.command.user.LoginCommand;
import com.mamay.inspection.command.user.LogoutCommand;
import com.mamay.inspection.command.user.RegisterCommand;

public enum CommandType {

  // user commands
  LOGIN {
    {
      this.command = new LoginCommand();
    }
  },
  LOGOUT {
    {
      this.command = new LogoutCommand();
    }
  },
  REGISTER {
    {
      this.command = new RegisterCommand();
    }
  },

  // magazine commands
  SHOW_MAG_LIST {
    {
      this.command = new ShowMagListCommand();
    }
  },
  SHOW_MAG_ITEM {
    {
      this.command = new ShowMagItemCommand();
    }
  },
  ADD_MAGAZINE {
    {
      this.command = new AddMagazineCommand();
    }
  },
  UPDATE_MAGAZINE {
    {
      this.command = new UpdateMagazineCommand();
    }
  },
  DELETE_MAGAZINE {
    {
      this.command = new DeleteMagazineCommand();
    }
  },
  SELECT_MAGAZINE {
    {
      this.command = new SelectMagazineCommand();
    }
  },
  NEW_MAGAZINE {
    {
      this.command = new NewMagazineCommand();
    }
  },

  // subscription commands
  ADD_SUBSCRIPTION {
    {
      this.command = new AddSubscriptionCommand();
    }
  },
  UPDATE_SUBSCRIPTION {
    {
      this.command = new UpdateSubscriptionCommand();
    }
  },
  DELETE_SUBSCRIPTION {
    {
      this.command = new DeleteSubscriptionCommand();
    }
  },
  SELECT_SUBSCRIPTION {
    {
      this.command = new SelectSubscriptionCommand();
    }
  },
  NEW_SUBSCRIPTION {
    {
      this.command = new NewSubscriptionCommand();
    }
  },

  // reservation commands
  SHOW_RES_LIST {
    {
      this.command = new ShowResListCommand();
    }
  },
  SHOW_USER_ITEM {
    {
      this.command = new ShowUserItemCommand();
    }
  },
  ADD_RESERVATION {
    {
      this.command = new AddReservationCommand();
    }
  },
  DELETE_RESERVATION {
    {
      this.command = new DeleteReservationCommand();
    }
  },
  UPDATE_RESERVATION {
    {
      this.command = new UpdateReservationCommand();
    }
  },
  NEW_RESERVATION {
    {
      this.command = new NewReservationCommand();
    }
  },

  REDIRECTION {
    {
      this.command = new RedirectionCommand();
    }
  },
  CHANGE_LOCALE {
    {
      this.command = new ChangeLocaleCommand();
    }
  };
  ActionCommand command;

  public ActionCommand getCurrentCommand() {
    return command;
  }
}
