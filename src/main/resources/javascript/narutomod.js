
function initializeCoreMod() {

    var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');

    ASMAPI.log("INFO", "Testing log function");

    // Locations are relative to the resources folder. Not the current file.
    ASMAPI.loadFile('javascript/jsasmhelper.js');

    var logger = jsASMHelper.logger;

    logger.info("Here is an info message");
    logger.warn("Here is a warning message");
    logger.error("Here is an error message");
    logger.debug("Here is a debug message");
    logger.fatal("Here is a fatal message");

    /**
     * You can use the mixin or at reference for help e.g. net.minecraft.client.gui.screen.MainMenuScreen init()V #init
     * Descriptors for the types of target
     * https://github.com/MinecraftForge/CoreMods/blob/e6fed88bfcb29bc062c04310f18ebe2777582d03/src/main/java/net/minecraftforge/coremod/CoreMod.java#L66
     * see net.minecraftforge.coremod.CoreMod.buildCore
     *
     * At time of writing target parameters for each 'type'
     *
     * METHOD
     *     class - string of class name
     *     methodName - string of method name
     *     methodDesc - string of method description
     * CLASS
     *     name - string of class name
     *       or
     *     names - function that takes a list of classes and returns an array of targets
     * FIELD
     *     class - string of class name
     *     fieldName - string of field name
     *
     * transformer - look at org.objectweb.asm for more info
     * METHOD - org.objectweb.asm.tree.MethodNode
     */
    return {
        'mainmenuinit': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.client.gui.screen.MainMenuScreen',
                //'methodName': Java.type("net.minecraftforge.coremod.api.ASMAPI").mapMethod('funcname'),
                'methodName': 'init',
                'methodDesc': '()V'
            },
            'transformer': function(method) {
                jsASMHelper.logger.info("Transformed init()V");

                var methodCall = jsASMHelper.class("com/sekwah/narutomod/coremod/CoreModTestCalls")
                    .method("basicTestCall")
                    .voidDesc();

                methodCall.insertInto(method);

                // var methodCall = jsASMHelper.class("com/sekwah/generictestmod/coremod/CoreModTestCalls")
                //     .method("testCall")
                //     .voidDesc().print().buildMethodCall();
                //
                // //net.minecraft.client.gui.screen.MainMenuScreen init()V #init
                // jsASMHelper.class("net/minecraft/client/gui/screen/MainMenuScreen")
                //     .method("init").voidDesc().node(method).insertMethodBefore(methodCall);

                return method;
            }
        },
        'mainmenuclass': {
            'target': {
                'type': 'CLASS',
                'name': 'net.minecraft.client.gui.screen.MainMenuScreen',
            },
            'transformer': function (classNode) {
                logger.info(classNode);
                logger.info("Injecting from class scope");
                var methodCall = jsASMHelper.class("com/sekwah/narutomod/coremod/CoreModTestCalls")
                    .method("classTestCall")
                    .voidDesc().print().buildMethodCall();

                /*jsASMHelper.class("net/minecraft/client/gui/screen/MainMenuScreen")
                     .method("init").voidDesc().node(classNode).insertMethodBefore(methodCall);*/
                return classNode;
            }
        }
    }
}
