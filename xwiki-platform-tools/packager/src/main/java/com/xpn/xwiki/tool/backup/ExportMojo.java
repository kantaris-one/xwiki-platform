/*
 * Copyright 2006, XpertNet SARL, and individual contributors as indicated
 * by the contributors.txt.
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
package com.xpn.xwiki.tool.backup;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

import com.xpn.xwiki.XWikiException;

/**
 * Maven 2 plugin to export a set of XWiki documents from an existing database to the file system.
 *
 * @version $Id: ImportMojo.java 1632 2006-11-23 16:34:23Z vmassol $
 * @goal export
 */
public class ExportMojo extends AbstractMojo
{
    /**
     * @parameter default-value = "xwiki"
     * @see com.xpn.xwiki.tool.backup.Exporter#exportDocuments(java.io.File, String, java.io.File)
     */
    private String databaseName;

    /**
     * @parameter default-value = "${basedir}/src/main/packager/hibernate.cfg.xml"
     * @see com.xpn.xwiki.tool.backup.Exporter#exportDocuments(java.io.File, String, java.io.File)
     */
    private File hibernateConfig;

    /**
     * @parameter default-value = "${project.build.directory}/export"
     * @see com.xpn.xwiki.tool.backup.Exporter#exportDocuments(java.io.File, String, java.io.File)
     */
    private File exportDirectory;

    /**
     * {@inheritDoc}
     * @see org.apache.maven.plugin.AbstractMojo#execute()
     */
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        // Ensure that the export directory exists before performing the export
        this.exportDirectory.mkdirs();

        Exporter exporter = new Exporter();

        try {
            exporter.exportDocuments(this.exportDirectory, this.databaseName, this.hibernateConfig);
        } catch (XWikiException e) {
            throw new MojoExecutionException("Failed to export XWiki documents", e);
        }
    }
}
