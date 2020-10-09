/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

// It's assumed that Jenkins has been configured to implicitly load the vars/xwikiModule.groovy library which exposes
// the "xwikiModule" global function/DSL.
// Note that the version used is the one defined in Jenkins but it can be overridden as follows:
// @Library("XWiki@<branch, tag, sha1>") _
// See https://github.com/jenkinsci/workflow-cps-global-lib-plugin for details.

node() {
  // Build without integration and functional tests (we override the "profiles" since by defaut they'd run these tests).
  // We do this so that we can get published artifacts even when integration and functional tests fail and so that we 
  // get results faster for devs.
  xwikiBuild {
    profiles = ''
    // We need a display for the CKBuilder. Note that we don't need xvnc for the functional tests themselves since they
    // execute inside Docker containers.
    xvnc = true
  }
  // Run the integration tests (since we don't override the "profiles", the default profiles will run integration and 
  // functional tests). Thus we specfify the "pom" parameter to only execute the integration tests from the "plugins"
  // module.
  xwikiBuild {
    pom = 'plugins/pom.xml'
  }
  // Run the functional (docker) tests (since we don't override the "profiles", the default profiles will run integration
  // and functional tests). Thus we specify the "pom" parameter to only execute the functional tests from the "test/tests"
  // module (the PO will have been built by the first "xwikiBuild" step above.
  xwikiBuild {
    pom = 'test/tests/pom.xml'
  }
}
