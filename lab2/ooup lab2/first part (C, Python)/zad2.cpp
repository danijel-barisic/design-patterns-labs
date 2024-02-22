#include <iostream>
#include <set>
#include <string>
#include <vector>

template <typename Iterator, typename Predicate>
Iterator mymax(Iterator first, Iterator last, Predicate pred) {
    Iterator max = first;
    for (Iterator it = first; it != last; ++it) {
        if (pred(*it, *max)) {
            max = it;
        }
    }
    return max;
}

bool gt_int(const int &a, const int &b) {
    return a > b;
}

bool gt_char(const char &a, const char &b) {
    return a > b;
}

bool gt_str(const std::string &a, const std::string &b) {
    return a > b;
}

int main() {
    int arr_int[] = {1, 3, 5, 7, 4, 6, 9, 2, 0};
    const int *maxint = mymax(&arr_int[0], &arr_int[sizeof(arr_int) / sizeof(*arr_int)], gt_int);
    std::cout << *maxint << "\n";

    char arr_char[] = "Suncana strana ulice";
    const char *maxchar = mymax(&arr_char[0], &arr_char[sizeof(arr_char) / sizeof(*arr_char)], gt_char);
    std::cout << *maxchar << "\n";

    std::string arr_str[] = {
        "Gle", "malu", "vocku", "poslije", "kise",
        "Puna", "je", "kapi", "pa", "ih", "njise"};
    const std::string *maxstr = mymax(&arr_str[0], &arr_str[sizeof(arr_str) / sizeof(*arr_str)], gt_str);
    std::cout << *maxstr << "\n";

    std::vector<int> vi = {2, 3, 8, 1};
    std::vector<int>::iterator maxvec = mymax(vi.begin(), vi.end(),
                                              gt_int);
    std::cout << *maxvec << "\n";

    std::set<int> si = {1, 3, 5, 7};
    std::set<int>::iterator maxset = mymax(si.begin(), si.end(),
                                           gt_int);
    std::cout << *maxset << "\n";

    return 0;
}
