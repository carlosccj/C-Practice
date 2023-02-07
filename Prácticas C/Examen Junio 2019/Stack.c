/*
 * Stack.c
 *
 *  Created on: 11 jun. 2019
 *      Author: galvez
 */
#include <stdio.h>
#include <stdlib.h>
#include "Stack.h"

// Creates an empty stack.
T_Stack create() {
    T_Stack res = NULL;
    return res;
}

// Returns true if the stack is empty and false in other case.
int isEmpty(T_Stack q) {
    int ok = 1; // 1 = TRUE, 0 = FALSE
    if (q != NULL) {
        ok = 0;
    }
    return ok;
}

// Inserts a number into the stack.
void push(T_Stack *pq, int operand) {
    T_Stack newOperand = (T_Stack) malloc (sizeof(T_Stack));
    if (newOperand == NULL) {
        perror("Error en push: no se ha podido reservar memoria\n");
        exit(-1);
    }
    newOperand->next = NULL;
    newOperand->number = operand;
    T_Stack curr = *pq;
    if(*pq == NULL) {
        *pq = newOperand;
    } else {
        while(curr->next != NULL) {
        curr = curr->next;
     }
        curr->next = newOperand;
    }
}

// "Inserts" an operator into the stack and operates.
// Returns true if everything OK or false in other case.
int pushOperator(T_Stack *pq, char operator) {
    int ok = 1; // 1 = TRUE, 0 = FALSE
    T_Stack op1 = *pq;
    if (op1->next == NULL) {
        ok = 0;
    } else {
        T_Stack op2 = op1->next;
        while(op2->next != NULL) {
            op1 = op2;
            op2 = op2->next;
        }
        int res = calculator(operator, op1->number, op2->number);
        op1->next = NULL;
        op1->number = res;
        free(op2);
        return ok;
    }
    return ok;
}

int calculator(char operator, int a, int b) {
    int result = 0;
    switch(operator) {
        case '+':
        result = a + b;
        break;
        case '-':
        result = a - b;
        break;
        case '*':
        result = a * b;
        break;
        case '/':
        result = a / b;
        break;
    }
    return result;
}

// Puts into data the number on top of the stack, and removes the top.
// Returns true if everything OK or false in other case.
int pop(T_Stack *pq, int *data) {
    int ok = 1; // 1 = TRUE, 0 = FALSE
    T_Stack ant = NULL;
    T_Stack curr = *pq;
    if(curr == NULL) {
        ok = 0;
    } else if (curr->next == NULL) {
        *data = (*pq)->number;
    } else {
        while(curr->next != NULL) {
            ant = curr;
            curr = curr->next;
        }
        *data = curr->number;
        ant->next = NULL;
        free(curr);
    }
    return ok;
}

// Frees the memory of a stack and sets it to empty.
void destroy(T_Stack *pq) {
    T_Stack curr = *pq;
    while((*pq) != NULL) {
        curr = *pq;
        *pq = (*pq)->next;
        free(curr);
    }
}
