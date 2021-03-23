package com.pavelhaleta.neurodiary.viewmodel.other

enum class ContactMessage (val NAME: String){
    ACCOUNT_NAME_LENGTH("Too long account name! It must be shorted than 50 symbols!"),
    ACCOUNT_NAME_EMPTY("Account name cannot be empty!"),
    ACCOUNT_NAME_EXIST("This name has already user!"),
    PASSWORD_LENGTH("Too long password! It must be shorted than 50 symbols!"),
    PASSWORD_EMPTY("Password cannot be empty!"),
    PASSWORD_WRONG("Wrong password!");

    override fun toString(): String {
        return NAME
    }
}