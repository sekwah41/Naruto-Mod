/*
If you reuse this file in any projects please leave this message in
Naruto Mod https://github.com/sekwah41/NarutoMod

You dont need to make your project open source to use any of these though if you make any new methods it would be
appreciated if you could make a pr if they seem like generally helpful methods. As long as we see it as helpful even
if there isn't an example of its usecase we will include it.

Changes
 - Put any changes you made here
 */

/*
 * Dev notes
 *
 * Currently testing methods. Will clean it up to be easier to use once I have done the experimentation I need to.
 *
 * The main objective of this file is to try to make the asm more human readable and shorter rather than easier to do.
 * If you don't know what you are doing you should expect horrible errors though that should not defer you from trying.
 * Just make sure you ask yourself, is it possible without asm? This should be done as a last resort for most things.
 *
 * Anything stored in the global scope will be loaded into other files so if you want to keep it clean i would recommend
 * running any large code planning to be reused in functions that output a usable json object for the library.
 *
 * Probably best to abbreviate anything you dont want to be interact with other code with something. We will use gtm_
 * for generic test mod to avoid clashes.
 *
 * When writing js in these files remember it is heavily outdated. You cant run any fancy new JS. I believe it is ES5
 * with a little of ES6 implemented though I may be wrong.
 *
 * Have to use the old method of creating classes.
 */

/**
 * Useful to check
 * https://github.com/MinecraftForge/CoreMods/blob/master/src/main/java/net/minecraftforge/coremod/api/ASMAPI.java
 * Setup library object to allow easier more clear calling
 * TODO map out functions once it has been decided what the basics of this file will do.
 */
function setupJSASMHelper (){
    var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');

    /**
     * @returns {{warn: warn, debug: debug, error: error, info: info, fatal: fatal}}
     */
    var logger = function() {
        function info(message) {
            ASMAPI.log('INFO', message);
        }
        function warn(message) {
            ASMAPI.log('WARN', message);
        }
        function error(message) {
            ASMAPI.log('ERROR', message);
        }
        function fatal(message) {
            ASMAPI.log('FATAL', message);
        }
        function debug(message) {
            ASMAPI.log('DEBUG', message);
        }
        function trace(message) {
            ASMAPI.log('TRACE', message);
        }
        return {
            info: info,
            warn: warn,
            error: error,
            fatal: fatal,
            debug: debug,
        };
    }();



    /**
     *
     * @return {class: getClass} if the insert was successful.
     * /
    function insertBeforeMethod(method) {

    }

    // function hookAtHead(method) {
    //
    //
    //     // TODO get first and inject before that
    // }

// Designed to function similar to the mixin "HEAD"
//     function hookAtReturn(method) {
//
//         var Opcodes = Java.type('org.objectweb.asm.Opcodes');
//
//         // TODO Get opcode of return and inject at all before that.
//
//         /*var arrayLength = method.instructions.size();
//         for (var i = 0; i < arrayLength; ++i) {
//             var instruction = method.instructions.get(i);
//             print(instruction);
//             print(instruction.getOpcode());
//             /!*if (instruction.getOpcode() == Opcodes.ICONST_1) {
//                 var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');
//                 var newInstruction = new InsnNode(Opcodes.ICONST_0);
//                 method.instructions.insertBefore(instruction, newInstruction);
//                 method.instructions.remove(instruction);
//                 print("Transformed!");
//                 break;
//             }*!/
//         }*/
//     }
    /**
     * Possibly rewrite as a json builder so that
     V                          void
     Z                          boolean
     B                          byte
     C                          char
     S                          short
     I                          int
     J                          long
     F                          float
     D                          double
     Lfull/path/Class;          full/path/Class
     */
    function QueryConstructor(clazz) {
        this._class = clazz;
        this._method;
        this._descriptor;
        this._methodType = ASMAPI.MethodType.STATIC;
        this._methodNode;
        this._classNode;

        /**
         * Copies the data so any further edits wont alter the old constructors
         * @return QueryConstructor
         */
        this.clone = function(){
            var qc = new QueryConstructor(this._class);
            qc._method = this._method;
            qc._descriptor = this._descriptor;
            qc._methodType = this._methodType;
            qc._methodNode = this._methodNode;
            qc._classNode = this._classNode;
            return qc;
        }

        // Class
        /**
         * @return QueryConstructor
         */
        this.class = function(clazz) {
            this._class = clazz;
            return this;
        }

        // Method
        /**
         * @return QueryConstructor
         */
        this.method = function(method) {
            this._method = method;
            return this;
        }

        /**
         * @return QueryConstructor
         */
        this.methodNode = function(node) {
            this._methodNode = node;
            return this;
        }
        // Descriptors
        /**
         * @return QueryConstructor
         */
        this.desc = function(descriptor) {
            this._descriptor = descriptor;
            return this;
        }

        /**
         * @return QueryConstructor
         */
        this.classDesc = function(classPath) {
            return this.desc("L" + classPath + ";");
        }

        /**
         * @return QueryConstructor
         */
        this.voidDesc = function() {
            return this.desc("()V");
        }

        /**
         * @return QueryConstructor
         */
        this.boolDesc = function() {
            return this.desc("()Z");
        }

        /**
         * @return QueryConstructor
         */
        this.byteDesc = function() {
            return this.desc("()B");
        }

        /**
         * @return QueryConstructor
         */
        this.charDesc = function() {
            return this.desc("()C");
        }

        /**
         * @return QueryConstructor
         */
        this.shortDesc = function() {
            return this.desc("()S");
        }

        /**
         * @return QueryConstructor
         */
        this.intDesc = function() {
            return this.desc("()I");
        }

        /**
         * @return QueryConstructor
         */
        this.longDesc = function() {
            return this.desc("()L");
        }

        /**
         * @return QueryConstructor
         */
        this.floatDesc = function() {
            return this.desc("()F");
        }

        /**
         * @return QueryConstructor
         */
        this.doubleDesc = function() {
            return this.desc("()D");
        }

        /**
         * @return QueryConstructor
         */
        this.print = function() {
            logger.info("Debug info for QueryConstructor");
            logger.info("class:", this._class);
            logger.info("method:", this._method);
            logger.info("descriptor:", this._descriptor);
            logger.info("methodType:", this._methodType);
            return this;
        }

        // What to do with the data collected.
        /**
         * Needs class, method, descriptor and methodType is optional.
         * @returns {*}
         */
        this.buildMethodCall = function() {
            if(!this._class) logger.error("buildMethodCall: Missing class")
            if(!this._method) logger.error("buildMethodCall: Missing method");
            if(!this._descriptor) logger.error("buildMethodCall: Missing descriptor");
            if(!this._methodType) logger.error("buildMethodCall: Missing methodType");

            if(this._class && this._method && this._descriptor && this._methodType) {
                logger.debug("Building new method.");
                return ASMAPI.buildMethodCall(this._class, this._method, this._descriptor, this._methodType);
            }
        }

        this.insertInto = function(node) {
            node.instructions.insert(this.buildMethodCall());
            return this;
        }

        // TODO add class based methods. Though these should do for now for injecting hooks into places

        /**
         * @param methodCall
         * @param insertMode
         * @return boolean if the method was inserted successfully
         */
        this.insertMethod = function(methodCall, insertMode) {
            return ASMAPI.insertInsnList(this._methodNode, this._methodType, this._class, this._method, this._descriptor,
                ASMAPI.listOf(methodCall), insertMode);

        }

        this.insertMethodBefore = function(methodCall) {
            return this.insertMethod(methodCall, ASMAPI.InsertMode.INSERT_BEFORE);
        }

        this.insertMethodAfter = function(methodCall) {
            return this.insertMethod(methodCall, ASMAPI.InsertMode.INSERT_AFTER);
        }

        this.insertMethodReplace = function(methodCall) {
            return this.insertMethod(methodCall, ASMAPI.InsertMode.REMOVE_ORIGINAL);
        }
    }

    /**
     *
     * @param node
     */
    function outputNodeInstructions(node) {
        var arrayLength = node.instructions.size();
        for (var i = 0; i < arrayLength; ++i) {
            var instruction = node.instructions.get(i);
            logger.info("Instruction")
            logger.info(instruction);
            logger.info(instruction.getOpcode());
            logger.info(instruction.name);
            logger.info(instruction.desc);
            if(instruction.next) logger.info(instruction.next.owner);
            logger.info("")
        }
    }

    /**
     * @return QueryConstructor
     */
    function createQueryConstructor() {
        return new QueryConstructor();
    }

    /**
     * @return QueryConstructor
     */
    function createQueryConstructorWithClass(clazz) {
        return new QueryConstructor(clazz)
    }

    return {
        logger: logger,
        qc: createQueryConstructor,
        class: createQueryConstructorWithClass,
        printNode: outputNodeInstructions
    };
}

var jsASMHelper = setupJSASMHelper();
