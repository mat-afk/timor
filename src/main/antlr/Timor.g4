grammar Timor;

@header {
package org.timor;
import org.timor.TimorListener;
import org.timor.TimorParser;
import org.timor.TimorVisitor;
}

// parser rules
compilationUnit : statement* EOF; // EOF: End of file
statement: ( print | variable ) SEMICOLON;
variable: VARIABLE_KEYWORD IDENTIFIER EQUALS value;
print: PRINT IDENTIFIER;
value: NUMBER | STRING;

// lexer rules (tokens)
VARIABLE_KEYWORD: 'var';
IDENTIFIER: [A-Za-z][._\-A-Za-z0-9]*;
PRINT: 'print';

// types
NUMBER: [0-9]+;
STRING: SINGLE_QUOTE .*? SINGLE_QUOTE
      | DOUBLE_QUOTE .*? DOUBLE_QUOTE;

// chars
EQUALS: '=';
DOUBLE_QUOTE: '"';
SINGLE_QUOTE: '\'';
SEMICOLON: ';';

// white space
WS: [ \t\n\r]+ -> skip;