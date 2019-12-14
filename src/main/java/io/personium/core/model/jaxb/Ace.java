/**
 * personium.io
 * Copyright 2014 FUJITSU LIMITED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.personium.core.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

/**
 * D: JAXB object corresponding to ace tag.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "DAV:", name = "ace", propOrder = {"principal", "grant", "inherited"})
public final class Ace {
    /**
     * Principal.
     */
    @XmlElement(namespace = "DAV:", name = "principal")
    Principal principal;
    /**
     * Grant.
     */
    @XmlElement(namespace = "DAV:", name = "grant")
    Grant grant;
    /**
     * Grant.
     */
    @XmlElement(namespace = "DAV:", name = "inherited")
    Inherited inherited;

    /**
     * @return Principal/href
     */
    public String getPrincipalHref() {
        if (this.principal == null) {
            return null;
        }
        return this.principal.href;
    }
    /**
     * @param href href value to set
     */
    public void setPrincipalHref(String href) {
        if (this.principal == null) {
            this.principal = new Principal();
        }
        this.principal.href = href;
        this.principal.all = null;
    }
    /**
     */
    public void setPrincipalAll() {
        if (this.principal == null) {
            this.principal = new Principal();
        }
        this.principal.href = null;
        this.principal.all = "all";
    }

    /**
     * @return Principal/all
     */
    public String getPrincipalAll() {
        if (this.principal == null) {
            return null;
        }
        return this.principal.all;
    }
    /**
     * @return String representation of privileges
     */
    public List<String> getGrantedPrivilegeList() {
        List<String> ret = new ArrayList<String>();
        if (grant == null) {
            return ret;
        }
        List<Privilege> privList = grant.privileges;
        if (privList != null) {
            for (Privilege priv : privList) {
                ret.add(priv.toString());
            }
        }
        return ret;
    }
    /**
     * Add a granted privilege.
     * @param privilege
     */
    public void addGrantedPrivilege(String privilege) {
        if (this.grant == null) {
            this.grant = new Grant();
            this.grant.privileges = new ArrayList<>();
        }
        // TODO After quit using JAXB , this part will be much simpler.
        // (Privilege body should just be text rather than element)
        Privilege p = new Privilege();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc;
        try {
            doc = dbf.newDocumentBuilder().newDocument();
            p.body = doc.createElementNS("DAV:", privilege);
            this.grant.privileges.add(p);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get inherited href.
     * @return inherited href
     */
    public String getInheritedHref() {
        if (this.inherited == null) {
            return null;
        }
        return this.inherited.href;
    }

    /**
     * set inherited href.
     * @param href href
     */
    public void setInheritedHref(String href) {
        if (href != null) {
            if (this.inherited == null) {
                this.inherited = new Inherited();
            }
            this.inherited.setHref(href);
        } else {
            this.inherited = null;
        }
    }
}
