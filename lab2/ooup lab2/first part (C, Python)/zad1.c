#include <stdio.h>
#include <string.h>

const void* mymax(const void *base, size_t nmemb, size_t size,
                  int (*compar)(const void *, const void *)) {
    const char *p = (const char*)base;
    const void *max = p;

    for (size_t i = 1; i < nmemb; ++i) {
        if (compar(p + i * size, max) > 0) {
            max = p + i * size;
        }
    }

    return max;
}

int gt_int(const void* a, const void* b) {
    int a_val = *(const int*) a;
    int b_val = *(const int*) b;
    return a_val > b_val;
}

int gt_char(const void* a, const void* b) {
    char a_val = *(const char*) a;
    char b_val = *(const char*) b;
    return a_val > b_val;
}

int gt_str(const void* a, const void* b) {
    const char* a_str = *(const char**) a;
    const char* b_str = *(const char**) b;
    return strcmp(a_str, b_str) > 0;
}

int main() {
    int arr_int[] = { 1, 3, 5, 7, 4, 6, 9, 2, 0 };
    int n_int = sizeof(arr_int) / sizeof(int);
    int* max_int = (int*) mymax(arr_int, n_int, sizeof(int), gt_int);
    printf("Max int: %d\n", *max_int);

    char arr_char[] = "Suncana strana ulice";
    int n_char = strlen(arr_char);
    char* max_char = (char*) mymax(arr_char, n_char, sizeof(char), gt_char);
    printf("Max char: %c\n", *max_char);

    const char* arr_str[] = {
       "Gle", "malu", "vocku", "poslije", "kise",
       "Puna", "je", "kapi", "pa", "ih", "njise"
    };
    int n_str = sizeof(arr_str) / sizeof(char*);
    const char** max_str = (const char**) mymax(arr_str, n_str, sizeof(char*), gt_str);
    printf("Max str: %s\n", *max_str);

    return 0;
}