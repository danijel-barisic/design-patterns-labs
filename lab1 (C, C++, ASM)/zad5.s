	.file	"zad5.cpp"
	.intel_syntax noprefix
	.section	.text$_ZN1D4prvaEv,"x"
	.linkonce discard
	.align 2
	.globl	__ZN1D4prvaEv
	.def	__ZN1D4prvaEv;	.scl	2;	.type	32;	.endef
__ZN1D4prvaEv:
LFB12:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	mov	eax, 42
	pop	ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE12:
	.section	.text$_ZN1D5drugaEi,"x"
	.linkonce discard
	.align 2
	.globl	__ZN1D5drugaEi
	.def	__ZN1D5drugaEi;	.scl	2;	.type	32;	.endef
__ZN1D5drugaEi:
LFB13:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	mov	eax, DWORD PTR [ebp+8]
	mov	eax, DWORD PTR [eax]
	mov	eax, DWORD PTR [eax]
	mov	edx, DWORD PTR [ebp+8]
	mov	DWORD PTR [esp], edx
	call	eax
	mov	edx, eax
	mov	eax, DWORD PTR [ebp+12]
	add	eax, edx
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE13:
	.section .rdata,"dr"
LC0:
	.ascii "Prva: %d\12\0"
LC1:
	.ascii "Druga: %d\12\0"
	.text
	.globl	__Z18call_virtual_funcsP1B
	.def	__Z18call_virtual_funcsP1B;	.scl	2;	.type	32;	.endef
__Z18call_virtual_funcsP1B:
LFB14:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 40
	mov	eax, DWORD PTR [ebp+8]
	mov	eax, DWORD PTR [eax]
	mov	DWORD PTR [ebp-12], eax
	mov	eax, DWORD PTR [ebp-12]
	mov	eax, DWORD PTR [eax]
	mov	DWORD PTR [ebp-16], eax
	mov	eax, DWORD PTR [ebp-12]
	add	eax, 4
	mov	eax, DWORD PTR [eax]
	mov	DWORD PTR [ebp-20], eax
	mov	eax, DWORD PTR [ebp-16]
	call	eax
	mov	DWORD PTR [esp+4], eax
	mov	DWORD PTR [esp], OFFSET FLAT:LC0
	call	_printf
	mov	DWORD PTR [esp+4], 3
	mov	eax, DWORD PTR [ebp+8]
	mov	DWORD PTR [esp], eax
	mov	eax, DWORD PTR [ebp-20]
	call	eax
	mov	DWORD PTR [esp+4], eax
	mov	DWORD PTR [esp], OFFSET FLAT:LC1
	call	_printf
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE14:
	.def	___main;	.scl	2;	.type	32;	.endef
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB15:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	and	esp, -16
	sub	esp, 32
	call	___main
	mov	eax, OFFSET FLAT:__ZTV1D+8
	mov	DWORD PTR [esp+28], eax
	lea	eax, [esp+28]
	mov	DWORD PTR [esp], eax
	call	__Z18call_virtual_funcsP1B
	mov	eax, 0
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE15:
	.globl	__ZTV1D
	.section	.rdata$_ZTV1D,"dr"
	.linkonce same_size
	.align 4
__ZTV1D:
	.long	0
	.long	__ZTI1D
	.long	__ZN1D4prvaEv
	.long	__ZN1D5drugaEi
	.globl	__ZTI1D
	.section	.rdata$_ZTI1D,"dr"
	.linkonce same_size
	.align 4
__ZTI1D:
	.long	__ZTVN10__cxxabiv120__si_class_type_infoE+8
	.long	__ZTS1D
	.long	__ZTI1B
	.globl	__ZTS1D
	.section	.rdata$_ZTS1D,"dr"
	.linkonce same_size
__ZTS1D:
	.ascii "1D\0"
	.globl	__ZTI1B
	.section	.rdata$_ZTI1B,"dr"
	.linkonce same_size
	.align 4
__ZTI1B:
	.long	__ZTVN10__cxxabiv117__class_type_infoE+8
	.long	__ZTS1B
	.globl	__ZTS1B
	.section	.rdata$_ZTS1B,"dr"
	.linkonce same_size
__ZTS1B:
	.ascii "1B\0"
	.ident	"GCC: (MinGW.org GCC-6.3.0-1) 6.3.0"
	.def	_printf;	.scl	2;	.type	32;	.endef
