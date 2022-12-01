#include "LifeConway.h"

const int ArgcOffline = 4;

using namespace std;
using namespace lifeConway;

int main(int argc, char **argv) {
    LifeConway x(argc, argv);
    OfflineInterface offlineInterface;
    OnlineInterface onlineInterface;
    if (argc == ArgcOffline) x.setGameMode(&offlineInterface);
    else x.setGameMode(&onlineInterface);
    x.startGame();
}
