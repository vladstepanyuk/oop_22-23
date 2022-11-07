#include "LifeConway.h"


using namespace std;

int main(int argc, char **argv) {
    LifeConway x(argc, argv);
    OfflineInterface oflineInterface;
    OnlineInterface onlineInterface;
    if (argc == 4) x.setGameMode(&oflineInterface);
    else x.setGameMode(&onlineInterface);
    x.startGame();
}