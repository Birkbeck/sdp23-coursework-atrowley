    mov EAX 2
    mov EBX 1
f1: sub EAX EBX
    jnz EAX f2
    out EAX