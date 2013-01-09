
This guide details the build process on an AMD64 instance of Debian GNU/Linux testing 'wheezy' 2012-12.


INSTALL
=======

Prerequisites
-------------

Install the necessary packages:

	apt-get install   ant gcc git make openjdk-6-jre openjdk-6-jdk tidy 


And maybe (but still testing requirements):

	apt-get install   libgcj13-awt openjdk-6-source  javahelp2 subversion libx11-dev


Definitely still testing this approach:
	apt-get install unixodbc
	wget    http://archive.debian.org/debian/pool/non-free/s/sun-java5/sun-java5-bin_1.5.0-22-0lenny1_amd64.deb			\
			http://archive.debian.org/debian/pool/non-free/s/sun-java5/sun-java5-jdk_1.5.0-22-0lenny1_amd64.deb			\
			http://archive.debian.org/debian/pool/non-free/s/sun-java5/sun-java5-jre_1.5.0-22-0lenny1_all.deb 			\
			http://archive.debian.org/debian/pool/non-free/s/sun-java5/sun-java5-demo_1.5.0-22-0lenny1_amd64.deb
	dpkg --install sun-java5*

	update-alternatives --config java
	# select 1.5

	update-alternatives --config javac
	# again, select 1.5 version


Alternative - grab java (sun) 1.6 from ubuntu repos:

Create file: /etc/apt/sources.list.d/ppa-webupd8team-java.list
	deb http://ppa.launchpad.net/webupd8team/java/ubuntu precise main

# @TODO write up guide for adding gpg key

Then:
	apt-get install oracle-java6-installer
Say yes to the onerous licensing cruft

	



Build process
-------------

	git clone git://github.com/jedd/LightZone.git
	cd LightZone

	# Pick your actual java home here, if it's not set:
	# export JAVA_HOME=/usr/lib/jvm/java-6-openjdk-amd64
	export JAVA_HOME=/usr/lib/jvm/java-1.5.0-sun


	ant





Known or suspected brokenness
-----------------------------
* OpenJDK 1.6 or 1.7 may be problematic 
* Sun-Java-1.5 is not a panacea
* Use of com.sun. packages will end in tears
* jhindexer seems to be virtually unknown now, but required by 'ant help' (linux)
* Original (binary) seems to have been built on a system with:
	* Sun-Java-1.5
	* libstdc++5 (though build appears to be dynamic)





