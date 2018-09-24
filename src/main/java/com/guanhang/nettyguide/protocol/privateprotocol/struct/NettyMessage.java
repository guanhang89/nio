/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.guanhang.nettyguide.protocol.privateprotocol.struct;

/**
 * @author lilinfeng
 * @date 2014年3月14日
 * @version 1.0
 */
public final class NettyMessage {

    private Header header;

    private Object body;


    public final Header getHeader() {
	return header;
    }

    public final void setHeader(Header header) {
	this.header = header;
    }

    public final Object getBody() {
	return body;
    }

    public final void setBody(Object body) {
	this.body = body;
    }

    @Override
    public String toString() {
	return "NettyMessage [header=" + header + "]";
    }
}
