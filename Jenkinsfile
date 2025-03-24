pipeline {
    agent any

    environment {
        DOCKER_USER = 's00ya'                    // DockerHub 아이디
        IMAGE_NAME = 'api-hospital'              // Docker 이미지 이름
        IMAGE_TAG = "${BUILD_NUMBER}"            // Jenkins 빌드 번호 기준으로 태그 지정
        FULL_IMAGE_NAME = "${DOCKER_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
        NAMESPACE = 'jjs'                        // 본인 네임스페이스!
    }

    stages {

        stage('Git Clone') {
            steps {
                echo "✅ GitHub에서 코드 클론 중..."
                git branch: 'main', url: 'https://github.com/s00ya/be12-3rd-ah_four-quedoc.git'
            }
        }

                stage('Gradle Build') {
                    steps {
                        echo "Add Permission"
                        sh 'chmod +x gradlew'

                        echo "Build"
                        sh './gradlew :api-hospital:bootJar --no-daemon --stacktrace --info'
                    }
                }

        stage('Docker Build & Push') {
            steps {
                echo "✅ Docker 이미지 빌드 시작!"
                script {
                    docker.build("${FULL_IMAGE_NAME}", './api-hospital')
                }

                echo "✅ Docker Hub로 푸시 중..."
                script {
                    withDockerRegistry([credentialsId: 'DOCKER_HUB']) {
                        docker.image("${FULL_IMAGE_NAME}").push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo "✅ 쿠버네티스 배포 중..."
                script {
                    sh """
                        kubectl set image deployment/api-hospital-v2 api-hospital=${FULL_IMAGE_NAME} -n ${NAMESPACE} --record
                    """
                }
            }
        }

        stage('Manual Approval to Full Release') {
            steps {
                input message: '카나리 테스트 완료! 전체 배포 전환할까요?'
            }
        }

        stage('Scale Up V2, Scale Down V1') {
            steps {
                echo "✅ V2로 스케일업 중..."
                sh """
                    kubectl scale deployment api-hospital-v2 --replicas=4 -n ${NAMESPACE}
                    kubectl scale deployment api-hospital-v1 --replicas=0 -n ${NAMESPACE}
                """
            }
        }

        stage('Clean Canary Resources') {
            steps {
                echo "✅ 불필요한 자원 정리 중..."
                sh "kubectl delete deployment api-hospital-v2 -n ${NAMESPACE}"
            }
        }
    }

    post {
        success {
            echo '🎉 배포 성공!!'
        }
        failure {
            echo '🚨 배포 실패!! 바로 확인하세요!!'
        }
    }
}
