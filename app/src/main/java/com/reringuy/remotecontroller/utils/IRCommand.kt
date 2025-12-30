package com.reringuy.remotecontroller.utils

import com.reringuy.remotecontroller.model.IrCode

enum class IRCommand(val irCode: IrCode) {
    ON_OFF(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E040BF")),
    SOURCE(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0807F")),
    RIGHT(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E046B9")),
    LEFT(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0A659")),
    UP(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E006F9")),
    DOWN(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E08679")),
    SELECT(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E016E9")),
    VOL_PLUS(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0E01F")),
    VOL_MINUS(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0D02F")),
    MUTE(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0F00F")),
    CH_PLUS(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E048B7")),
    CH_MINUS(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E008F7")),
    MENU(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E058A7")),
    SMART_HUB(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E09E61")),
    GUIDE(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0F20D")),
    TOOLS(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0D22D")),
    INFO(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0F807")),
    RETURN(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E01AE5")),
    EXIT(IrCode(protocol = "SAMSUNG", bits = 32, data = "0xE0E0B44B"))
}