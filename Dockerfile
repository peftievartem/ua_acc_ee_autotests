FROM  maven:3.8.6-openjdk-11-slim

EXPOSE 8080

SHELL ["/bin/bash", "-xo", "pipefail", "-c"]
ENV DEBIAN_FRONTEND noninteractive
RUN set -x;

RUN apt-get install  \
            vim \
            htop \
            curl \
            git

#             gcc --no-install-recommends --no-install-suggests  --assume-yes --auto-remove -y \
#             && apt-get clean \
#             && rm -rf /var/lib/apt/lists/*

CMD ["mvn"]
