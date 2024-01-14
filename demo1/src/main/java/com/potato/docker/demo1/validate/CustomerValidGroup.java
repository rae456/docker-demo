package com.potato.docker.demo1.validate;

import javax.validation.groups.Default;

public interface CustomerValidGroup extends Default {
    interface Crud extends CustomerValidGroup {
        interface Insert extends Crud {

        }

        interface Update extends Crud {

        }

        interface Query extends Crud {

        }

        interface Delete extends Crud {

        }
    }
}
