grammar Timor;

@header {
package org.timor;
}

compilationUnit : ( print )* EOF;
program: print+;
print: PRINT value;
value: NUMBER | STRING;

NUMBER : [0-9]+;
STRING : '"'.*?'"';
PRINT: 'Print';

WS: [ \t\n\r]+ -> skip;