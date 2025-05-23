import request from '@/utils/request'

// 登录
export function userLogin(username, password) {
  return request({
    url: '/login',
    method: 'post',
    headers: {
      isToken: false
    },
    data: {'userName': username, 'password': password}
  })
}

export function userRegister(username, nickName, phonenumber, email, password) {
  return request({
    url: '/user/register',
    method: 'post',
    headers: {
      isToken: false
    },
    data: {"userName": username, "nickName": nickName, "phonenumber": phonenumber, "email": email, "password": password}
  })
}


export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

export function getUserInfo(userId) {
  return request({
    url: '/user/userInfo',
    method: 'get',
    params: {"userId": userId}
  })
}


export function savaUserInfo(userinfo) {
  return request({
    url: '/user/userInfo',
    method: 'put',
    data: userinfo
  })
}
