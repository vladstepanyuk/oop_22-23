

#include "Factory.h"

namespace {
    const int MuteNum = 0;
    const int MixNum = 2;
    const int MultNum = 1;

}
namespace soundProcessor {

    const std::vector<std::string> ConverterIds = {"mute",  "mult", "mix"};

    Converter *soundProcessor::Factory::newConverter(const std::string &converterId) {
        if (converterId == ConverterIds[MuteNum]) {
            Converter *newConv = new MuteConverter;
            return newConv;
        } else if (converterId == ConverterIds[MixNum]) {
            Converter *newConv = new MixConverter;
            return newConv;
        } else if (converterId == ConverterIds[MultNum]) {
            Converter *newConv = new MultConverter;
            return newConv;
        } else {
            throw std::exception("wrong converter id");
        }
    }
};
