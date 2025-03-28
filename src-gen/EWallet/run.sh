#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

java -cp aisco.product.ewallet --module-path aisco.product.ewallet -m aisco.product.ewallet &

wait