#ifndef TASK4_TUPLEPRINTER_H
#define TASK4_TUPLEPRINTER_H

#include <iostream>
#include <tuple>

namespace {
    template<class Ch, class Tr, typename... Args>
    class TuplePrinter {
    public:
        template<int n>
        void print(std::basic_ostream<Ch, Tr> &os, const std::tuple<Args...> &t){
            os << std::get<sizeof...(Args) - 1 - n>(t) << ' ';
            print<n-1>(os, t);
        }

        template<>
        void print<0>(std::basic_ostream<Ch, Tr> &os, const std::tuple<Args...> &t){
            os << std::get<sizeof...(Args) - 1>(t) << ' ';
        }
    };
}


namespace tuplePrinter {



    template<class Ch, class Tr, typename... Args>
    std::basic_ostream<Ch, Tr> &operator<<(std::basic_ostream<Ch, Tr> &os, const std::tuple<Args...> &t){
        TuplePrinter<Ch, Tr, Args...> printer;
        printer.print<sizeof...(Args) - 1>(os, t);
        return os;
    }




}
#endif //TASK4_TUPLEPRINTER_H
