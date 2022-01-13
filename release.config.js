var publishCmd = `
IMAGE_NAME="danysk/alchemist-sapere-tutorial"
docker build -t "$IMAGE_NAME:\${nextRelease.version}"
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin docker.io
docker push --all-tags "$IMAGE_NAME"
`
var config = require('semantic-release-preconfigured-conventional-commits');
config.plugins.push(
    [
        "@semantic-release/exec",
        {
            "publishCmd": publishCmd,
        }
    ],
    "@semantic-release/github",
    "@semantic-release/git",
)
module.exports = config
