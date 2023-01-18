
#ifndef TASK4_CSVPARSER_H
#define TASK4_CSVPARSER_H

#include <iostream>
#include <string>
#include <sstream>
#include "TuplePrinter.h"

namespace {
    const char StandardColumnsDelimiter = ',';
    const char StandardLinesDelimiter = '\n';
    const char StandardShielding = '"';
}


namespace parser {

    template<typename... Args>
    class CSVParser {
    public:
        CSVParser(std::istream &istream, int offset) : columnsDelimiter(StandardColumnsDelimiter),
                                                       linesDelimiter(StandardLinesDelimiter),
                                                       shielding(StandardShielding), istream1(istream),
                                                       offset(offset) {}

        void setOptions(const char &columnsDelimiter = StandardColumnsDelimiter,
                        const char &linesDelimiter = StandardLinesDelimiter,
                        const char &shielding = StandardShielding) {
            this->columnsDelimiter = columnsDelimiter;
            this->linesDelimiter = linesDelimiter;
            this->shielding = shielding;

        }

        virtual ~CSVParser() = default;

        bool getLine(std::tuple<Args...> &tuple) {
            std::string stringBuff1, stringBuff2;
            while (true) {
                std::getline(istream1, stringBuff2, linesDelimiter);
                int k = 0;
                for (int i = stringBuff2.size() - 1; i > 0; --i) {
                    if (stringBuff2[i] == shielding) k += 1;
                    else break;
                }
                stringBuff1 += stringBuff2;
                if (k % 2 == 0) break;
                stringBuff1 += linesDelimiter;
            }
            if (!(istream1.good() || istream1.eof())) throw std::exception("can't read");
            else if (istream1.eof()) return false;

            LineParser<sizeof...(Args)> parser;
            parser.pars(tuple, stringBuff1, 0, columnsDelimiter, shielding);

            return true;
        }

        bool skipLines() {
            std::string stringBuff2;
            for (int i = 0; i < offset; ++i) {
                while (true) {
                    std::getline(istream1, stringBuff2, linesDelimiter);
                    int k = 0;
                    for (int j = stringBuff2.size() - 1; j > 0; --j) {
                        if (stringBuff2[j] == shielding) k += 1;
                        else break;
                    }
                    if (k % 2 != 0) break;
                }
            }

            if (!(istream1.good() || istream1.eof())) throw std::exception("can't read");
            else if (istream1.eof()) return false;

            return true;
        }

        class CSVParserIterator {
        public:
            CSVParserIterator(bool isEOF, CSVParser<Args...> &parent, const std::tuple<Args...> &element) : isEOF(
                    isEOF), parent(parent), element(element) {}

            CSVParserIterator operator++() {
                isEOF = !parent.getLine(element);
                return *this;
            }

            CSVParserIterator(const CSVParserIterator &iterator) = default;

            CSVParserIterator &operator=(const CSVParserIterator &iterator) = default;

            bool operator==(const CSVParserIterator &iterator) {
                return (iterator.isEOF && this->isEOF) || isEqual<sizeof...(Args) - 1>(this->element, iterator.element);
            }

            bool operator!=(const CSVParserIterator &iterator) {
                return !(*this == iterator);
            }

            std::tuple<Args...> &operator*() {
                return element;
            }

        private:

            template<int n>
            bool isEqual(const std::tuple<Args...> &tuple1, const std::tuple<Args...> &tuple2) {
                return (std::get<n>(tuple1) == std::get<n>(tuple2)) && isEqual<n - 1>(tuple1, tuple2);
            }

            template<>
            bool isEqual<0>(const std::tuple<Args...> &tuple1, const std::tuple<Args...> &tuple2) {
                return std::get<0>(tuple1) == std::get<0>(tuple2);
            }


            bool isEOF;
            CSVParser<Args...> &parent;
            std::tuple<Args...> element;
        };

        CSVParserIterator begin() {
            if (skipLines()) {
                std::tuple<Args...> tuple;
                bool isEOF = !getLine(tuple);
                return CSVParserIterator(isEOF, *this, tuple);
            } else {
                std::tuple<Args...> tuple;
                return CSVParserIterator(true, *this, tuple);
            }

        }

        CSVParserIterator end() {
            std::tuple<Args...> tuple;
            return CSVParserIterator(true, *this, tuple);
        }


    private:
        template<int n>
        class LineParser {
            std::string processDelimiters(const std::string &line, int curPos, char delimiter,
                                          char shield, int &nextDelimiterPosition){
                std::string preParsedString;
                while (true) {
                    nextDelimiterPosition = line.find(delimiter, curPos);
                    if (nextDelimiterPosition == std::string::npos)
                        throw std::invalid_argument("can't read " + std::to_string(n) + " column");
                    preParsedString += line.substr(curPos, nextDelimiterPosition - curPos);
                    int k = 0;
                    for (int i = preParsedString.size() - 1; i >= 0; --i) {
                        if (preParsedString[i] == shield) k += 1;
                        else break;
                    }
                    if (k % 2 == 0) break;
                    preParsedString += delimiter;
                    curPos = nextDelimiterPosition + 1;
                }
                return preParsedString;
            }

            std::string processShields(const std::string &preParsedString, char shield){
                std::string parsedString;
                for (int i = 0; i < preParsedString.size(); ++i) {
                    int nextShieldingPosition = preParsedString.find(shield, i);
                    if (nextShieldingPosition == std::string::npos) {
                        parsedString += preParsedString.substr(i, preParsedString.size() - i);
                        break;
                    }
                    parsedString += preParsedString.substr(i, nextShieldingPosition - i);
                    parsedString += preParsedString[nextShieldingPosition + 1];
                    i = nextShieldingPosition + 2;

                }
                return parsedString;
            }
        public:
            void pars(std::tuple<Args...> &t, const std::string &line, int curPos, char delimiter,
                      char shield) {
                std::string parsedString, preParsedString;
                int nextDelimiterPosition;

                preParsedString = processDelimiters(line, curPos, delimiter, shield, nextDelimiterPosition);


                parsedString = processShields(preParsedString, shield);

                std::stringstream stringstream(parsedString);

                if ((stringstream >> std::get<sizeof...(Args) - n>(t)).rdbuf()->in_avail() != 0) {
                    throw std::invalid_argument("can't read " + std::to_string(sizeof...(Args) - n + 1) + " column");
                }


                curPos = nextDelimiterPosition + 1;
                LineParser<n - 1> parser;
                parser.pars(t, line, curPos, delimiter, shield);
            }
        };

        template<>
        class LineParser<1> {
            std::string processDelimiters(const std::string &line, int curPos, char delimiter,
                                          char shield, int &nextDelimiterPosition){
                std::string preParsedString;
                while (true) {
                    nextDelimiterPosition = line.find(delimiter, curPos);
                    if (nextDelimiterPosition == std::string::npos) {
                        preParsedString += line.substr(curPos, line.size() - curPos);
                        break;
                    }
                    preParsedString += line.substr(curPos, nextDelimiterPosition - curPos);
                    int k = 0;
                    for (int i = preParsedString.size() - 1; i >= 0; --i) {
                        if (preParsedString[i] == shield) k += 1;
                        else break;
                    }
                    if (k % 2 == 0) throw std::invalid_argument("can't read" + std::to_string(sizeof...(Args)) + "column");
                    preParsedString += delimiter;
                    curPos = nextDelimiterPosition + 1;
                }
                return preParsedString;
            }

            std::string processShields(const std::string &preParsedString, char shield){
                std::string parsedString;
                for (int i = 0; i < preParsedString.size(); ++i) {
                    int nextShieldingPosition = preParsedString.find(shield, i);
                    if (nextShieldingPosition == std::string::npos) {
                        parsedString += preParsedString.substr(i, preParsedString.size() - i);
                        break;
                    }
                    parsedString += preParsedString.substr(i, nextShieldingPosition - i);
                    parsedString += preParsedString[nextShieldingPosition + 1];
                    i = nextShieldingPosition + 2;

                }
                return parsedString;
            }

        public:
            void pars(std::tuple<Args...> &t, const std::string &line, int curPos, char delimiter,
                      char shield) {
                std::string parsedString, preParsedString;
                int nextDelimiterPosition;

                preParsedString = processDelimiters(line, curPos, delimiter, shield, nextDelimiterPosition);

                parsedString = processShields(preParsedString, shield);
                std::stringstream stringstream(parsedString);

                if ((stringstream >> std::get<sizeof...(Args) - 1>(t)).rdbuf()->in_avail() != 0) {
                    throw std::invalid_argument("can't read " + std::to_string(sizeof...(Args)) + " column");
                }

            }
        };


        char columnsDelimiter;
        char linesDelimiter;
        char shielding;
        std::istream &istream1;
        int offset;
    };


}


#endif
