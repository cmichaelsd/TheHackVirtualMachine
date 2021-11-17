# The Hack Virtual Machine

### Purpose
The Hack Virtual Machine exists for the purpose of translating Hack Bytecode into Hack Assembly Language which can then be built into machine language. This is built to the specifications outlined in the book "The Elements of Computing Systems".

### Specification
This project consists of three modules which interact in order to translate bytecode.

- Parser: provides services for reading a VM command, unpacking the command into its various components, and providing convenient access to these components.
- Code Writer: translates parsed VM command into assembly code.
- Virtual Machine Translator: the main program that drives the translation process.

### Parser
| Routine | Arguments | Returns | Function |
|---------|-----------|---------|----------|
| Constructor / initializer | Input file / stream | - | Opens the input file / stream, and gets ready to parse it. |
| hasMoreLines | - | boolean | Determines if there are more lines. |
| advance | - | - | Reads the next command from the input and makes it the current command. This routine should be called only if hasMoreLines is true. Initially there is no current command. |
| commandType | - | C_ARITHMETIC, C_PUSH, C_POP, C_LABEL, C_GOTO, C_IF, C_FUNCTION, C_RETURN, C_CALL (constant) | Returns a constant representing the type of the current command. If the current command is an arithmetic-logical command, returns C_ARITHMETIC. |
| arg1 | - | string | Returns the first argument of the current command. In the case of C_ARITHMETIC, the command itself (add, sub, etc) is returned. Should not be called if the current command is C_RETURN.
| arg2 | - | int | Returns the second argument of the current command. Should be called only if the current command is C_PUSH, C_POP, C_FUNCTION, or C_CALL. |

### Code Writer
| Routine | Arguments | Returns | Function |
|---------|-----------|---------|----------|
| Constructor / initializer | Output file / stream | - | Opens an output file / stream and gets ready to write into it. |
| writeArithmetic | command (string) | - | Writes to the output file the assembly code that implements the given arithmetic-logical command. |
| writePushPop | command (C_PUSH or C_POP), segment (string), index (int) | - | Writes to the output file the assembly code that implements the given arithmetic-logical command. |
| close | - | - | Closes the output file / stream. |

### Virtual Machine Translator
No API provided for this module.

### Testing
Unit testing exists for every module and a test exists for every method to ensure expected behavior.