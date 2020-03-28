#!/bin/sh

IMAGE="dmen/denv:1.0"
CONTAINER="gym-jee-denv"

USER_GROUP_NAME="user"
USER_GROUP_ID=$(id -g)
USER_NAME="user"
USER_ID=$(id -u)

AS_CONTAINER="gym-jee-as"

case $1 in
init)
	docker run -d -it \
	-v $(pwd):/mnt/work \
	--env USER_GROUP_NAME="$USER_GROUP_NAME" \
	--env USER_GROUP_ID="$USER_GROUP_ID" \
	--env USER_NAME="$USER_NAME" \
	--env USER_ID="$USER_ID" \
	--link $AS_CONTAINER \
	--name $CONTAINER \
	$IMAGE
	;;
start)
	docker start $CONTAINER
	;;
stop)
	docker stop $CONTAINER
	;;
remove)
	docker rm $CONTAINER
	;;
logs)
	docker logs $CONTAINER
	;;
exec)
	if ([ "$#" -eq "1" ]); then
		echo
		echo "Run a command as root:"
		echo "$0 exec bash -l"
		echo "$0 exec bash -l -c \"node -v\""
		echo "$0 exec bash -l -c \"java -version\""
		echo
		echo "Log as user:"
		echo "$0 exec su -l user -s /bin/bash"
		echo
		exit 1
	fi

	# $#    : the number of arguments, not counting $0
	# $@    : all positional parameters except $0
	# shift : discards the first parameter

	shift
	docker exec -it $CONTAINER "$@"
	;;
ip)
	docker inspect $CONTAINER | grep IPAddress | grep -v null | head -1 | cut -d'"' -f4
	;;
*)
	echo
	echo "Usage: $0 { init | start | stop | remove | logs | exec | ip }"
	echo
	exit 1
	;;
esac
