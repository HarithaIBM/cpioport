node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/cpioport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/cpioport.git'), string(name: 'PORT_DESCRIPTION', value: 'GNU cpio copies files into or out of a cpio or tar archive.' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
