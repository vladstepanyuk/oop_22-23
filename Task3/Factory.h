#pragma once
#include "Converter.h"
#include <vector>
#include <string>

namespace soundProcessor{
    extern const std::vector<std::string> ConverterIds;
    class Factory {
    public:
        Converter *newConverter(const std::string &converterId);
    };

}
