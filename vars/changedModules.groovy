def call(String commit = '', String relative = '') {
    echo "Fetching change sets from $commit, path: $path, exclude: $exclude"
    paths = changeSets commit, relative

    modules = []
    for (int i = 0; i < paths.size(); i++) {
        def path = paths[i]
        if (exclude == '' || !path.startsWith(exclude)) {
            if (path.split('/').size() > 1) {
                modules.add path.split('/')[0]
            }
        }
    }
    modules.toSet()

}

def changeSets(String commit, String relative = '') {
    def multiline = sh returnStdout: true, script: "git diff --name-only --relative=$relative $commit"
    echo "Changed files:\n$multiline"
    multiline.readLines()
}