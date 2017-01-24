HomeVLAT
HomeLONG

def get_home_location() {
    while not vehicle.home_location:
        cmds = vehicle.commands
        cmds.download()
        cmds.wait_ready()
        if not vehicle.home_location:
            print " Waiting for home location ..."
    HomeVLAT = vehicle.home_location.lat
    HomeLONG = vehicle.home_location.lon
    print "\n Home location: %s" % vehicle.home_location
}
